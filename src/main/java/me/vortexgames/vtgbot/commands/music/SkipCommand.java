package me.vortexgames.vtgbot.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.vortexgames.vtgbot.music.GuildMusicManager;
import me.vortexgames.vtgbot.music.PlayerManager;
import me.vortexgames.vtgbot.music.TrackScheduler;
import me.vortexgames.vtgbot.objects.ICommand;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

import java.util.List;

public class SkipCommand implements ICommand {


    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        TrackScheduler scheduler = musicManager.scheduler;
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

        scheduler.nextTrack();
        channel.sendMessage("Ik heb het liedje geskipt, op naar de volgende.").queue();
    }

    @Override
    public String getHelp() {
        return "Skips the current song.";
    }

    @Override
    public String getInvoke() {
        return "skip";
    }
}
