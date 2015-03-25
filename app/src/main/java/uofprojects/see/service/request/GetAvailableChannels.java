package uofprojects.see.service.request;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import uofprojects.see.service.response.AbstractResponse;
import uofprojects.see.service.response.GetAvailableChannelsResponse;
import uofprojects.see.util.ServiceUtil;

/**
 * Created by home on 2015-03-20.
 */
public class GetAvailableChannels extends AbstractRequest {

    @Override
    public AbstractResponse threadLogic() {
        try {
            HttpGet get = ServiceUtil.getRequest(ServiceUtil.GET_CHANNELS);
            JSONObject result = executeGet(get);


            if(!result.getBoolean(ServiceUtil.ResponseKeys.Success.getKey())){
                return new GetAvailableChannelsResponse(result.getString(ServiceUtil.ResponseKeys.Error.getKey()), false);
            }
            else{
                Object channels = result.get(ServiceUtil.ResponseKeys.Channels.getKey());
                if(channels instanceof JSONArray){
                    JSONArray channelsArray = (JSONArray)channels;
                    List<String> channelsList = new ArrayList<>(channelsArray.length());
                    for(int i=0; i<channelsArray.length(); i++) {
                        Object o = channelsArray.get(i);
                        if(o instanceof  String) {
                            String channel = (String) o;
                            channelsList.add(channel);
                        }
                    }

                    return new GetAvailableChannelsResponse(null, true, channelsList);
                }
                else{
                    return new GetAvailableChannelsResponse("The channels list is incompatible!", false);
                }
            }
        }
        catch (Exception e) {
            // Ignore
        }

        return new GetAvailableChannelsResponse("Get available channels failed!", false);
    }
}
