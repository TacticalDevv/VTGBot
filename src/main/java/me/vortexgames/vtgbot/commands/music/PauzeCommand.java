package me.vortexgames.vtgbot.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.vortexgames.vtgbot.music.GuildMusicManager;
import me.vortexgames.vtgbot.music.PlayerManager;
import me.vortexgames.vtgbot.objects.ICommand;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

import java.util.List;

public class PauzeCommand implements ICommand {


    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        AudioPlayer player = musicManager.player;
        AudioManager audioManager = event.getGuild().getAudioManager();

        VoiceChannel voiceChannel = audioManager.getConnectedChannel();

        if (player.getPlayingTrack() == null) {
            channel.sendMessage("De player is geen muziek aan het afspelen.").queue();
            return;
        }

        if (!voiceChannel.getMembers().contains(event.getMember())) {
            channel.sendMessage("U moet in de zelfde voicechannel als de bot zitten.").queue();
            return;
        }

        if (!player.isPaused()) {
            player.setPaused(true);
            channel.sendMessage("U bent nu gepauzeerd. :zzz:").queue();
        } else {
            player.setPaused(false);
            channel.sendMessage("U bent nu niet meer gepauzeerd. :watch:").queue();
        }

    }

    @Override
    public String getHelp() {
        return "Pause the current song";
    }

    @Override
    public String getInvoke() {
        return "pauze";
    }
}
