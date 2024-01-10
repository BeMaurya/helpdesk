package bemaurya.helpdesk.api;

import bemaurya.helpdesk.business.abstracts.ConfigServices;
import bemaurya.helpdesk.core.utilities.result.Result;
import bemaurya.helpdesk.core.utilities.result.ResultData;
import bemaurya.helpdesk.dto.request.Config.AddConfigRequest;
import bemaurya.helpdesk.entities.concreates.Config;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Config APIs")
@CrossOrigin
@Controller
@RestController
@RequestMapping("/api/config")
public class ConfigController {

    // inject ConfigServices
    private final ConfigServices configServices;

    // constructor injection
    public ConfigController(ConfigServices configServices) {
        this.configServices = configServices;
    }

    // add config
    @PostMapping("/")
    public Result saveConfig(@RequestHeader String token, @RequestBody AddConfigRequest addConfigRequest) {
        return configServices.add(token, addConfigRequest);
    }

    // get all config
    @GetMapping("/")
    public ResultData<List<Config>> getConfig(@RequestHeader String token) {
        return configServices.getAll(token);
    }

    // delete config
    @DeleteMapping("/{id}")
    public Result deleteConfig(@RequestHeader String token, @PathVariable long id) {
        return configServices.delete(token, id);
    }

    // get default config
    @GetMapping("/default")
    public ResultData<Config> getDefaultConfig(@RequestHeader String token) {
        return configServices.getDefault(token);
    }

    // set default config
    @PatchMapping("/default/{id}")
    public Result setDefaultConfig(@RequestHeader String token, @PathVariable long id) {
        return configServices.setDefault(token, id);
    }
}