package hhc.br.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mViewCrimes;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mViewCrimes = view.findViewById(R.id.view_crimes);
        mViewCrimes.setLayoutManager(new LinearLayoutManager(getActivity()));

        //wire the recycler view to the adapter
        updateUI();

        return view;
    }

    private class CrimeHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Crime mCrime;
        private TextView mTvTitle;
        private TextView mTvDate;
        private ImageView mImgSolved;

        public CrimeHolder(LayoutInflater inflater,
                           ViewGroup parent,
                           int viewType) {
            super(inflater.inflate(viewType == 1
                    ? R.layout.list_item_crime
                    : R.layout.list_item_crime_police, parent, false));
            itemView.setOnClickListener(this);
            mTvTitle = itemView.findViewById(R.id.crime_title);
            mTvDate = itemView.findViewById(R.id.crime_date);
            mImgSolved = itemView.findViewById(R.id.img_crime_solved);
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTvTitle.setText(crime.getTitle());
            mTvDate.setText(crime.getDate().toString());
            mImgSolved.setVisibility(mCrime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            return new CrimeHolder(inflater, parent, viewType);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            holder.bind(mCrimes.get(position));
        }

        @Override
        public int getItemViewType(int position) {
            /*
            1 regular
            2 requires police
             */
            return mCrimes.get(position).isRequiresPolice() ? 2 : 1;
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mViewCrimes.setAdapter(mAdapter);
    }
}
