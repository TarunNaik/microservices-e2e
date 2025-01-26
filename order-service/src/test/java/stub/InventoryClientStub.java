package stub;
import com.github.tomakehurst.wiremock.client.MappingBuilder;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {

    public static void stubInventoryCall(String skuCode) {
        // Stub the call to the inventory service
        stubFor(get(urlEqualTo("/v1/api/inventory/" + skuCode))
                .willReturn(aResponse().withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("10")));
    }

}
