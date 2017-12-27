package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 *
 * @author Lucas
 */
public class BigNumber {
    // this is used for the bigDecimal calculations - it stops after 36 digits when dividing, for example.
    // Google IEEE 754R Decimal128 format for more details
    private static final MathContext context = MathContext.DECIMAL128;
    
    
    private final BigInteger integer;
    private final BigInteger fraction;
    private final BigInteger fractionDivisor;
    
    /**
     * 
     * @return 
     */
    public BigInteger getInteger() {
        return integer;
    }

    // get the fraction 
    public BigInteger[] getFraction() {
        return new BigInteger[]{fraction, fractionDivisor};
    }

    public BigDecimal getBigDecimal() {
        BigDecimal output = new BigDecimal(integer, context);
        BigDecimal radixOutput = new BigDecimal(fraction, context);
        BigDecimal radixDivisor = new BigDecimal(fractionDivisor, context);
        radixOutput = radixOutput.divide(radixDivisor, context);
        output = output.add(radixOutput);
        return output;
    }

    // and or xor nand nor xnor not mod add subtract multiply divide
    public BigNumber divide(BigNumber other) {
        BigDecimal myDecimal = this.getBigDecimal();
        BigDecimal otherDecimal = other.getBigDecimal();
        BigDecimal output = myDecimal.divide(otherDecimal, context);
        return new BigNumber(output);
    }

    public BigNumber multiply(BigNumber other) {
        BigDecimal myDecimal = this.getBigDecimal();
        BigDecimal otherDecimal = other.getBigDecimal();
        BigDecimal output = myDecimal.multiply(otherDecimal, context);
        return new BigNumber(output);
    }

    public BigNumber subtract(BigNumber other) {
        BigDecimal myDecimal = this.getBigDecimal();
        BigDecimal otherDecimal = other.getBigDecimal();
        BigDecimal output = myDecimal.subtract(otherDecimal, context);
        return new BigNumber(output);
    }

    public BigNumber add(BigNumber other) {
        BigDecimal myDecimal = this.getBigDecimal();
        BigDecimal otherDecimal = other.getBigDecimal();
        BigDecimal output = myDecimal.add(otherDecimal, context);
        return new BigNumber(output);
    }

    public BigNumber mod(BigNumber other) {
        BigNumber divide = this.divide(other);
        BigDecimal fraction = new BigDecimal(divide.getFraction()[0], context);
        BigDecimal fractionDivisor = new BigDecimal(divide.getFraction()[1], context);
        BigDecimal output = fraction.divide(fractionDivisor, context);
        output = output.multiply(other.getBigDecimal(), context);
        return new BigNumber(output);
    }

    public BigNumber xnor(BigNumber other) {
        BigNumber output = this.xor(other);
        return output.not();
    }

    public BigNumber nor(BigNumber other) {
        BigNumber output = this.or(other);
        return output.not();
    }

    public BigNumber nand(BigNumber other) {
        BigNumber output = this.and(other);
        return output.not();
    }

    public BigNumber not() {
        BigInteger newInteger = this.integer.not();
        // throw away fraction divisor because we'll need to recalculate it
        BigInteger myFraction = this.getFraction()[0];

        BigInteger newFraction = myFraction.not();

        return new BigNumber(newInteger, newFraction);
    }

    public BigNumber xor(BigNumber other) {
        BigInteger newInteger = this.integer.xor(other.getInteger());

        // throw away fraction divisor because we'll need to recalculate it
        BigInteger myFraction = this.getFraction()[0];
        BigInteger otherFraction = other.getFraction()[0];

        BigInteger newFraction = myFraction.xor(otherFraction);

        return new BigNumber(newInteger, newFraction);
    }

    public BigNumber and(BigNumber other) {
        BigInteger newInteger = this.integer.and(other.getInteger());

        // throw away fraction divisor because we'll need to recalculate it
        BigInteger myFraction = this.getFraction()[0];
        BigInteger otherFraction = other.getFraction()[0];

        BigInteger newFraction = myFraction.and(otherFraction);
        
        return new BigNumber(newInteger, newFraction);
    }

    public BigNumber or(BigNumber other) {
        BigInteger newInteger = this.integer.or(other.getInteger());

        // throw away fraction divisor because we'll need to recalculate it
        BigInteger myFraction = this.getFraction()[0];
        BigInteger otherFraction = other.getFraction()[0];

        BigInteger newFraction = myFraction.or(otherFraction);

        return new BigNumber(newInteger, newFraction);
    }

    private BigNumber(BigInteger integer, BigInteger fraction) {
        this.integer = integer;
        this.fraction = fraction;
        BigInteger newFractionDivisor = null;
        if (fraction.equals(BigInteger.ZERO)) {
            newFractionDivisor = BigInteger.ONE;
        } else {
            newFractionDivisor = (new BigInteger("10", 10)).pow(fraction.toString().length());
        }
        this.fractionDivisor = newFractionDivisor;
    }

    private static BigInteger safeBigInteger(String input, int base) {
        BigInteger output = null;
        try {
            output = new BigInteger(input, base);
        } catch (NumberFormatException nfe) {
            output = BigInteger.ZERO;
        }
        return output;
    }

    public BigNumber(String input, int base) {
        String baseString = Integer.toString(base);
        String[] radixSplit = input.split("\\.", 2);

        for (int i = 0; i < radixSplit.length; i++) {
            radixSplit[i] = radixSplit[i].replaceAll("\\.", "");
        }

        this.integer = safeBigInteger(radixSplit[0], base);

        if (radixSplit.length == 2) {
            radixSplit[1] = radixSplit[1].replaceAll("0*$", "");
            if (!radixSplit[1].equals("")) {
                this.fraction = safeBigInteger(radixSplit[1], base);
                this.fractionDivisor = (safeBigInteger(baseString, 10)).pow(radixSplit[1].length());
            } else {
                this.fraction = BigInteger.ZERO;
                this.fractionDivisor = BigInteger.ONE;
            }
        } else {
            this.fraction = BigInteger.ZERO;
            this.fractionDivisor = BigInteger.ONE;
        }
    }

    public BigNumber(BigDecimal number) {
        this(number.toPlainString(), 10);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BigNumber)) {
            return false;
        }
        BigNumber otherNum = (BigNumber) other;
        // dumb test
        if (this.toString().equals(other.toString())) {
            return true;
        }
        BigInteger myFraction[] = this.getFraction();
        BigInteger otherFraction[] = otherNum.getFraction();
        return this.getInteger().equals(otherNum.getInteger())
                && myFraction[0].equals(otherFraction[0])
                && myFraction[1].equals(otherFraction[1]);
    }

    @Override
    public String toString() {
        return this.integer.toString() + " " + this.fraction.toString() + "/"
                + this.fractionDivisor.toString();
    }

}
