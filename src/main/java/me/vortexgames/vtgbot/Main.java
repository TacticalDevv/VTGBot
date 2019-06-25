package me.vortexgames.vtgbot;

import me.vortexgames.vtgbot.commands.SayCommand;
import me.vortexgames.vtgbot.commands.TicketCommand;
import me.vortexgames.vtgbot.listeners.AddReaction;
import me.vortexgames.vtgbot.listeners.WelkomMessage;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

public class Main {

    private static JDA jda;

    // LINK: https://discordapp.com/oauth2/authorize?client_id=593076297715154954&scope=bot&permissions=2146958847

    public static void main(String[] main) throws LoginException, InterruptedException {
        startBot();

        jda.addEventListener(new WelkomMessage());
        jda.addEventListener(new AddReaction());
        jda.addEventListener(new SayCommand());
        jda.addEventListener(new TicketCommand());
//        jda.addEventListener(new LeadMentionBlock());
    }

    private static void startBot() throws LoginException, InterruptedException {
        // TODO start bot
        jda = new JDABuilder(AccountType.BOT).setToken("NTkzMDc2Mjk3NzE1MTU0OTU0.XRIngQ.jSp7W7fbBUhcC69QaS6-G2HAM0U").buildBlocking();

        jda.getPresence().setGame(Game.playing("play.vortexgames.nl"));
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.setAutoReconnect(true);
        System.out.print("De bot is opgestart!!");
    }

    public static JDA getJda() {
        return jda;
    }
}
