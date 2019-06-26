package me.vortexgames.vtgbot;

import me.vortexgames.vtgbot.listeners.WelkomMessage;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.Random;

public class Main {

    private static JDA jda;

    // LINK: https://discordapp.com/oauth2/authorize?client_id=593076297715154954&scope=bot&permissions=2146958847

    private static final Random random = new Random();

    private Main() {

        CommandManager commandManager = new CommandManager(random);
        Listener listener = new Listener(commandManager);
        Logger logger = LoggerFactory.getLogger(Main.class);

//        WebUtils.setUserAgent();

//        EmbedUtils.setEmbedBuilder(
//                () -> new EmbedBuilder()
//                        .setColor(getRandomColor())
//                        .setFooter("{MenuDocs}", null)
//                        .setTimestamp(Instant.now())
//        );


        try {
            logger.info("Booting");
            new JDABuilder(AccountType.BOT)
                    .setToken("NTkzMDc2Mjk3NzE1MTU0OTU0.XRIngQ.jSp7W7fbBUhcC69QaS6-G2HAM0U")
                    .setGame(Game.playing("play.vortexgames.nl"))
                    .addEventListener(listener).addEventListener(new WelkomMessage())
                    .build().awaitReady();
            logger.info("Running");
        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }
    }

    public static Color getRandomColor() {
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        return new Color(r, g, b);
    }

    public static void main(String[] args) {
        new Main();
    }


    public static JDA getJda() {
        return jda;
    }
}
