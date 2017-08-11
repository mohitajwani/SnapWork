package snapwork.com.mohittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by snap34 on 11/8/17.
 */

public interface ApiInterface {
    @GET("poi.json")
    Call<List<Offer>> getAllOffers();
}