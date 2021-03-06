package hhc.br.criminalintent;

import android.content.Context;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        //Random random = new SecureRandom();
        mCrimes = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 3 == 0);
            crime.setRequiresPolice(i % 5 == 0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime c : mCrimes){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }
}
