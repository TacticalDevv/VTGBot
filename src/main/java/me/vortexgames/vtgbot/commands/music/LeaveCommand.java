package me.vortexgames.vtgbot.commands.music;

import me.vortexgames.vtgbot.objects.ICommand;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

import java.util.List;

public class LeaveCommand implements ICommand {


    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {

        TextChannel channel = event.getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();

        if (!audioManager.isConnected()) {
            channel.sendMessage("Ik zit niet in een voicechannel.").queue();
            return;
        }

        VoiceChannel voiceChannel = audioManager.getConnectedChannel();

        if (!voiceChannel.getMembers().contains(event.getMember())) {
            channel.sendMessage("U moet in de zelfde voicechannel als de bot zitten.").queue();
            return;
        }

        audioManager.closeAudioConnection();
        channel.sendMessage("Ben de voicechannel aan het leaven.").queue();
    }

    @Override
    public String getHelp() {
        return "Makes the bot leave your channel";
    }

    @Override
    public String getInvoke() {
        return "leave";
    }
}
