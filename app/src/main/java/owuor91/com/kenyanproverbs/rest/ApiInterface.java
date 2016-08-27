package owuor91.com.kenyanproverbs.rest;

import java.util.List;

import owuor91.com.kenyanproverbs.models.Proverb;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by owuor91 on 8/27/16.
 */
public interface ApiInterface {
    @GET("api/v1/proverbs")
    Call<List<Proverb>> getAllProverbs();

    @POST("api/v1/proverbs")
    Call<Proverb> postNewProverb(@Body Proverb proverb);
}
