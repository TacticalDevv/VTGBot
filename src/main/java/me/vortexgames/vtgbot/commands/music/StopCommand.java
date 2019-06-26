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

public class StopCommand implements ICommand {


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

        musicManager.scheduler.getQueue().clear();
        musicManager.player.stopTrack();
        musicManager.player.setPaused(false);

        event.getChannel().sendMessage("De speler is gestopt, en de queue is gecleared.").queue();
    }

    @Override
    public String getHelp() {
        return "Stops the music players";
    }

    @Override
    public String getInvoke() {
        return "stop";
    }
}
