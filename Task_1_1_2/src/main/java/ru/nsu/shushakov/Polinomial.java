package ru.nsu.shushakov;

import java.util.Arrays;

/**
 * main class.
 *
 * <p>
 * polynomCof - array of polynom's coefficients.
 * pow - the greatest polynim's power.
 * </p>
 */
public class Polinomial {
    int[] polynomCof;
    int pow;

    /**
     * constructor of class Polinomial.
     *
     * @param cofs - we take int[] to make a polynom.
     */
    public Polinomial(int[] cofs) {
        this.polynomCof = cofs;
        this.pow = cofs.length;
    }

    /**
     * adding one polynom to another.
     *
     * @param pol2 - second polynom.
     *
     * @return pol1 + pol2.
     */
    public Polinomial plus(Polinomial pol2) {
        int[] newPol = new int[Math.max(this.pow, pol2.pow)];
        for (int i = 0; i < Math.min(this.pow, pol2.pow); i++) {
            newPol[i] = this.polynomCof[i] + pol2.polynomCof[i];
        }
        if (this.polynomCof.length > pol2.polynomCof.length) {
            for (int i = Math.min(this.pow, pol2.pow); i < Math.max(this.pow, pol2.pow); i++) {
                newPol[i] = this.polynomCof[i];
            }
        } else {
            for (int i = Math.min(this.pow, pol2.pow); i < Math.max(this.pow, pol2.pow); i++) {
                newPol[i] = pol2.polynomCof[i];
            }
        }
        Polinomial retPol = new Polinomial(newPol);
        return retPol;
    }

    /**
     * substract one polynom from another.
     *
     * @param pol2 - second polynom.
     *
     * @return pol1 - pol2.
     */
    public Polinomial minus(Polinomial pol2) {
        int[] newPol = new int[Math.max(this.pow, pol2.pow)];
        for (int i = 0; i < Math.min(this.pow, pol2.pow); i++) {
            newPol[i] = this.polynomCof[i] - pol2.polynomCof[i];
        }
        if (this.polynomCof.length > pol2.polynomCof.length) {
            for (int i = Math.min(this.pow, pol2.pow); i < Math.max(this.pow, pol2.pow); i++) {
                newPol[i] = this.polynomCof[i];
            }
        } else {
            for (int i = Math.min(this.pow, pol2.pow); i < Math.max(this.pow, pol2.pow); i++) {
                newPol[i] = -pol2.polynomCof[i];
            }
        }
        Polinomial retPol = new Polinomial(newPol);
        return retPol;
    }

    /**
     * multiply one polynom on another.
     *
     * @param pol2 - second polynom.
     *
     * @return pol1 * pol2.
     */
    public Polinomial mult(Polinomial pol2) {
        if (this.polynomCof.length == 0 && pol2.polynomCof.length == 0) {
            return pol2;
        }
        else if (this.polynomCof.length == 0) {
            return pol2;
        }
        else if (pol2.polynomCof.length == 0) {
            return this;
        }
        int[] newPol = new int[this.polynomCof.length + pol2.polynomCof.length - 1];
        for (int i = 0; i < this.polynomCof.length; i++) {
            for (int j = 0; j < pol2.polynomCof.length; j++) {
                newPol[i + j] += this.polynomCof[i] * pol2.polynomCof[j];
            }
        }
        Polinomial retPol = new Polinomial(newPol);
        return retPol;
    }

    /**
     * gives value in spot.
     *
     * @param spot - point that we replace x with.
     *
     * @return y = pol(x).
     */
    public int inSpot(int spot) {
        int resInSpot = this.polynomCof[0];
        for (int i = 1; i < this.polynomCof.length; i++) {
            resInSpot += Math.pow(spot, i) * this.polynomCof[i];
        }
        return resInSpot;
    }

    /**
     * check for fools.
     *
     * @return 0 every time.
     */
    public int inSpot() {
        return 0;
    }

    /**
     * gives an i derivative.
     * <p>
     * takes order than multiply coef on it's position
     * </p>
     * @param order - order of derivative.
     *
     * @return this'.
     */
    public Polinomial iDerivative(int order) {
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < this.polynomCof.length; j++) {
                this.polynomCof[j] = this.polynomCof[j] * j;
            }
            if (this.polynomCof.length > 1)
                this.polynomCof = Arrays.copyOfRange(this.polynomCof, 1, this.polynomCof.length);
        }
        return this;
    }

    /**
     * check equality.
     *
     * @param pol2 - second polynom.
     *
     * @return true or false.
     */
    public Boolean isEqual(Polinomial pol2) {
        if (pol2 == null)
            return false;
        return Arrays.equals(this.polynomCof, pol2.polynomCof);
    }

    /**
     * check sign.
     *
     * @param checkSign - checks if int '>' 0 or not.
     *
     * @return string + or -.
     */
    public String sign(int checkSign) {
        if (checkSign > 0)
            return "+";
        return "-";
    }

    /**
     * gives a string representation of polynom.
     *
     * @return string polynom.
     */
    public String polToStr() {
        String res = "";
        if (this.polynomCof.length == 0)
            return "...";
        if (this.polynomCof.length < 2) {
            res += this.polynomCof[0];
            return res;
        } else if (this.polynomCof.length == 2) {
            if (this.polynomCof[1] != 0)
                res += this.polynomCof[1] + "x" + " " + sign(polynomCof[0]) + " ";
            res += Math.abs(this.polynomCof[0]);
            return res;
        } else {
            if (this.polynomCof[this.polynomCof.length - 1] != 0) {
                res += this.polynomCof[this.polynomCof.length - 1] + "x" +
                        "^" + Math.abs(this.polynomCof.length - 1) +
                        " " + sign(this.polynomCof[this.polynomCof.length - 2]) + " ";
            }
            for (int i = this.polynomCof.length - 2; i > 1; i--) {
                if (this.polynomCof[i] == 0) {
                    continue;
                }
                res += Math.abs(this.polynomCof[i]) + "x" +
                        "^" + i + " " + sign(this.polynomCof[i - 1]) + " ";
            }
            if (this.polynomCof[1] != 0) {
                res += Math.abs(this.polynomCof[1]) + "x" + " " + sign(this.polynomCof[0]) + " ";
            }
            res += Math.abs(this.polynomCof[0]);
            return res;
        }
    }
}
