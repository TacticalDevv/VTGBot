package me.vortexgames.vtgbot.commands.music;

import me.vortexgames.vtgbot.Constants;
import me.vortexgames.vtgbot.music.PlayerManager;
import me.vortexgames.vtgbot.objects.ICommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.GuildVoiceState;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PlayCommand implements ICommand {


    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        PlayerManager manager = PlayerManager.getInstance();

        TextChannel channel = event.getChannel();

        AudioManager audioManager = event.getGuild().getAudioManager();

        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("Join eerst een voicechannel.").queue();
            return;
        }

        VoiceChannel voiceChannel = memberVoiceState.getChannel();
        Member selfMember = event.getGuild().getSelfMember();

        if (!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)) {
            channel.sendMessageFormat("Missing permissions to join %s", voiceChannel).queue();
            return;
        }

        audioManager.openAudioConnection(voiceChannel);

        if (args.isEmpty()) {
            channel.sendMessage("Geef alstublieft een argument mee. ").queue();
            return;
        }

        String input = String.join(" ", args);

        if (!isUrl(input) && !input.startsWith("ytsearch:")) {
            channel.sendMessage("Geef alstublieft een geldige youtube, soundcloud of bandcamp link mee.").queue();
            return;
        }

        manager.loadAndPlay(event.getChannel(), input);
    }

    private boolean isUrl(String input) {
        try {
            new URL(input);

            return true;
        } catch (MalformedURLException ignored) {
            return false;
        }
    }

    @Override
    public String getHelp() {
        return "Plays a song\n" +
                "Usage: `" + Constants.PREFIX + getInvoke() + " <song url>`";
    }

    @Override
    public String getInvoke() {
        return "play";
    }
}
