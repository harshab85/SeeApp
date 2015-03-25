package uofprojects.see.service.request;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import uofprojects.see.service.response.AbstractResponse;
import uofprojects.see.service.response.PollResponse;
import uofprojects.see.util.ServiceUtil;
import uofprojects.see.util.StorageUtil;

/**
 * Created by home on 2015-03-22.
 */
public class Poll extends AbstractRequest {

    @Override
    public AbstractResponse threadLogic() {
        try {
            HttpPost post = ServiceUtil.getPostRequest(ServiceUtil.POLL, ServiceUtil.getPollRequestEntity());
            JSONObject result = executePost(post);

            if (!result.getBoolean(ServiceUtil.ResponseKeys.Success.getKey())) {
                return new PollResponse(result.getString(ServiceUtil.ResponseKeys.Error.getKey()), false);
            }
            else {
                return new PollResponse(null, true, result.getString(ServiceUtil.ResponseKeys.VideoUrl.getKey()));
            }
        } catch (Exception e) {
            // Ignore
        }

        return new PollResponse("Poll failed!", false);
    }
}
