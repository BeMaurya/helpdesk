package bemaurya.helpdesk.business.abstracts;

import bemaurya.helpdesk.core.utilities.result.Result;
import bemaurya.helpdesk.core.utilities.result.ResultData;
import bemaurya.helpdesk.dto.request.Config.AddConfigRequest;
import bemaurya.helpdesk.entities.concreates.Config;

import java.util.List;

public interface ConfigServices {
    Result add(String token, AddConfigRequest addConfigRequest);
    ResultData<List<Config>> getAll(String token);
    Result delete(String token, long id);
    Result setDefault(String token, long id);
    ResultData<Config> getDefault(String token);
}