package ru.nsu.shushakov;

import java.util.Arrays;

public class Polinomial {
    int[] polynomCof;
    int pow;

    public Polinomial(int[] cofs) {
        this.polynomCof = cofs;
        this.pow = cofs.length;
    }

    public Polinomial plus(Polinomial pol2) {
        int[] newPol = new int[Math.max(this.pow, pol2.pow)];

        int i;
        for(i = 0; i < Math.min(this.pow, pol2.pow); ++i) {
            newPol[i] = this.polynomCof[i] + pol2.polynomCof[i];
        }

        if (this.polynomCof.length > pol2.polynomCof.length) {
            for(i = Math.min(this.pow, pol2.pow); i < Math.max(this.pow, pol2.pow); ++i) {
                newPol[i] = this.polynomCof[i];
            }
        } else {
            for(i = Math.min(this.pow, pol2.pow); i < Math.max(this.pow, pol2.pow); ++i) {
                newPol[i] = pol2.polynomCof[i];
            }
        }

        Polinomial retPol = new Polinomial(newPol);
        return retPol;
    }

    public Polinomial minus(Polinomial pol2) {
        int[] newPol = new int[Math.max(this.pow, pol2.pow)];

        int i;
        for(i = 0; i < Math.min(this.pow, pol2.pow); ++i) {
            newPol[i] = this.polynomCof[i] - pol2.polynomCof[i];
        }

        if (this.polynomCof.length > pol2.polynomCof.length) {
            for(i = Math.min(this.pow, pol2.pow); i < Math.max(this.pow, pol2.pow); ++i) {
                newPol[i] = this.polynomCof[i];
            }
        } else {
            for(i = Math.min(this.pow, pol2.pow); i < Math.max(this.pow, pol2.pow); ++i) {
                newPol[i] = -pol2.polynomCof[i];
            }
        }

        Polinomial retPol = new Polinomial(newPol);
        return retPol;
    }

    public Polinomial mult(Polinomial pol2) {
        if (this.polynomCof.length == 0 && pol2.polynomCof.length == 0) {
            return pol2;
        } else if (this.polynomCof.length == 0) {
            return pol2;
        } else if (pol2.polynomCof.length == 0) {
            return this;
        } else {
            int[] newPol = new int[this.polynomCof.length + pol2.polynomCof.length - 1];

            for(int i = 0; i < this.polynomCof.length; ++i) {
                for(int j = 0; j < pol2.polynomCof.length; ++j) {
                    newPol[i + j] += this.polynomCof[i] * pol2.polynomCof[j];
                }
            }

            Polinomial retPol = new Polinomial(newPol);
            return retPol;
        }
    }

    public int inSpot(int spot) {
        int resInSpot = this.polynomCof[0];

        for(int i = 1; i < this.polynomCof.length; ++i) {
            resInSpot = (int)((double)resInSpot + Math.pow((double)spot, (double)i) * (double)this.polynomCof[i]);
        }

        return resInSpot;
    }

    public String inSpot() {
        return "ты дурак?";
    }

    public Polinomial iDerivative(int order) {
        for(int i = 0; i < order; ++i) {
            for(int j = 0; j < this.polynomCof.length; ++j) {
                this.polynomCof[j] *= j;
            }

            if (this.polynomCof.length > 1) {
                this.polynomCof = Arrays.copyOfRange(this.polynomCof, 1, this.polynomCof.length);
            }
        }

        return this;
    }

    public Boolean isEqual(Polinomial pol2) {
        return this.polynomCof == pol2.polynomCof ? true : false;
    }

    public String sign(int checkSign) {
        return checkSign > 0 ? "+" : "-";
    }

    public String polToStr() {
        String res = "";
        if (this.polynomCof.length == 0) {
            return "ты дурак?";
        } else if (this.polynomCof.length < 2) {
            res = res + this.polynomCof[0];
            return res;
        } else if (this.polynomCof.length == 2) {
            if (this.polynomCof[1] != 0) {
                res = res + this.polynomCof[1] + "x " + this.sign(this.polynomCof[0]) + " ";
            }

            res = res + Math.abs(this.polynomCof[0]);
            return res;
        } else {
            if (this.polynomCof[this.polynomCof.length - 1] != 0) {
                res = res + this.polynomCof[this.polynomCof.length - 1] + "x^" + Math.abs(this.polynomCof.length - 1) + " " + this.sign(this.polynomCof[this.polynomCof.length - 2]) + " ";
            }

            for(int i = this.polynomCof.length - 2; i > 1; --i) {
                if (this.polynomCof[i] != 0) {
                    res = res + Math.abs(this.polynomCof[i]) + "x^" + i + " " + this.sign(this.polynomCof[i - 1]) + " ";
                }
            }

            if (this.polynomCof[1] != 0) {
                res = res + Math.abs(this.polynomCof[1]) + "x " + this.sign(this.polynomCof[0]) + " ";
            }

            res = res + Math.abs(this.polynomCof[0]);
            return res;
        }
    }
}
