package owuor91.com.kenyanproverbs.rest;

import java.util.List;

import owuor91.com.kenyanproverbs.models.Proverb;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by owuor91 on 8/27/16.
 */
public interface ApiInterface {
    @GET("api/v1/proverbs")
    Call<List<Proverb>> getAllProverbs();
}
