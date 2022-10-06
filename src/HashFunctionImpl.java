import java.util.Random;

public class HashFunctionImpl implements HashFunction {
    private int prime;
    private int _n_;
    private int _a_;
    private int _b_;

    public HashFunctionImpl( int prime,int _n_ ,Random rng_hash_parameter) {
        this.prime = prime;
        this._n_ = _n_;
        this._a_ = rng_hash_parameter.nextInt(1,prime-1);
        this._b_ = rng_hash_parameter.nextInt(0,prime-1);
    }
    @Override
    public int twoUniversalHashing(int value)
    {
        int hashValue=((_a_*value+_b_)%prime)%_n_;
        return hashValue;
    }

}
