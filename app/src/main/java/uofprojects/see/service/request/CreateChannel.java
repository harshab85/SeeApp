package uofprojects.see.service.request;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import uofprojects.see.service.response.AbstractResponse;
import uofprojects.see.service.response.CreateChannelResponse;
import uofprojects.see.util.ServiceUtil;
import uofprojects.see.util.StorageUtil;

/**
 * Created by home on 2015-03-20.
 */
public class CreateChannel extends AbstractRequest {

    private String channelName;

    public CreateChannel(String channelName){
        this.channelName = channelName;
    }

    @Override
    public AbstractResponse threadLogic() {
        try {
            JSONObject params = new JSONObject();
            params.put(ServiceUtil.PayloadKeys.ChannelName.getKey(), channelName);
            params.put(ServiceUtil.PayloadKeys.UserId.getKey(), StorageUtil.getStringValue(ServiceUtil.PayloadKeys.UserId.getKey()));
            StringEntity stringEntity = new StringEntity(params.toString(), ServiceUtil.DEFAULT_CHARSET);

            HttpPost post = ServiceUtil.getPostRequest(ServiceUtil.CREATE_CHANNEL, stringEntity);
            final JSONObject result = executePost(post);

            return new CreateChannelResponse(result.getString(ServiceUtil.ResponseKeys.Error.getKey()), result.getBoolean(ServiceUtil.ResponseKeys.Success.getKey()));
        }
        catch (Exception e) {
            // Ignore
        }

        return new CreateChannelResponse("Create channel failed!", false);
    }

}
