package Models;

import com.google.gson.annotations.SerializedName;

public record modelApi (String result,
                        @SerializedName(value = "error-type")
                        String error_type,
                        String conversion_rate,
                        String conversion_result,
                        String time_last_update_utc) {

}
