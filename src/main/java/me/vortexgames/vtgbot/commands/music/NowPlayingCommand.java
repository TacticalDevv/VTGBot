package me.vortexgames.vtgbot.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import me.vortexgames.vtgbot.Main;
import me.vortexgames.vtgbot.music.GuildMusicManager;
import me.vortexgames.vtgbot.music.PlayerManager;
import me.vortexgames.vtgbot.objects.ICommand;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NowPlayingCommand implements ICommand {


    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        AudioPlayer player = musicManager.player;

        if (player.getPlayingTrack() == null) {
            channel.sendMessage("De player is geen muziek aan het afspelen.").queue();
            return;
        }

        AudioTrackInfo info = player.getPlayingTrack().getInfo();

        EmbedBuilder builder = new EmbedBuilder().setColor(Main.getRandomColor()).appendDescription(String.format(
                "**Aan het spelen** [%s](%s)\n%s %s - %s",
                info.title,
                info.uri,
                player.isPaused() ? "\u23F8" : "▶",
                formatTime(player.getPlayingTrack().getPosition()),
                formatTime(player.getPlayingTrack().getDuration())
        ));

        channel.sendMessage(builder.build()).queue();
    }

    @Override
    public String getHelp() {
        return "Shows the currently playing track";
    }

    @Override
    public String getInvoke() {
        return "np";
    }

    private String formatTime(long timeInMillis) {
        final long hours = timeInMillis / TimeUnit.HOURS.toMillis(1);
        final long minutes = timeInMillis / TimeUnit.MINUTES.toMillis(1);
        final long seconds = timeInMillis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
