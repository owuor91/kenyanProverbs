package owuor91.com.kenyanproverbs.services;

import com.orm.SugarRecord;

import java.util.List;

import owuor91.com.kenyanproverbs.models.Proverb;
import owuor91.com.kenyanproverbs.rest.ApiClient;
import owuor91.com.kenyanproverbs.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by owuor91 on 8/27/16.
 */
public class ProverbsService {

    public void fetchProverbs(){
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<List<Proverb>> proverbsListCall  = apiInterface.getAllProverbs();
        proverbsListCall.enqueue(new Callback<List<Proverb>>() {
            @Override
            public void onResponse(Call<List<Proverb>> call, Response<List<Proverb>> response) {
                if (response.isSuccessful()){
                    List<Proverb> proverbList = response.body();
                    SugarRecord.deleteAll(Proverb.class);
                    SugarRecord.saveInTx(proverbList);
                }
            }

            @Override
            public void onFailure(Call<List<Proverb>> call, Throwable t) {

            }
        });
    }
}
