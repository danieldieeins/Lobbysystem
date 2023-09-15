package live.nerotv.lobby.managers;

import live.nerotv.user.User;
import org.geysermc.cumulus.form.ModalForm;
import org.geysermc.floodgate.api.FloodgateApi;

public class FormManager {

    public static void openBedrockJoinForm(User user) {
        ModalForm bedrockJoinForm = ModalForm.builder().title("Du nutzt Bedrock!").content("Du nutzt die Minecraft: Bedrock Edition! Das bedeutet, dass du dir aussuchen kannst, ob der Server sich auch dafür anpasst oder ob du im Java Edition Modus spielen willst. (Das kannst du später in den Einstellungen ändern)").button1("§2Bedrock Modus nutzen").button2("§4Java Modus nutzen").closedOrInvalidResultHandler(()->{
            openBedrockJoinForm(user);
        }).validResultHandler((form,result)->{
            String button = result.clickedButtonText();
            if(button.contains("Java")) {
                user.setSetting("bedrock mode","false");
            } else {
                user.setSetting("bedrock mode","true");
            }
        }).build();
        FloodgateApi.getInstance().sendForm(user.getUUID(),bedrockJoinForm);
    }
}