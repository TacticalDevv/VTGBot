package me.vortexgames.vtgbot.commands;

import me.vortexgames.vtgbot.Main;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.restaction.AuditableRestAction;

public class TicketCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot() || event.getAuthor().isFake() || event.isWebhookMessage()) return;

        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase("!ticket")) {
            Member member = event.getMember();

            String reden;

            args[0] = " ";
            StringBuilder stringBuilder = new StringBuilder();

            for (String arg : args) {
                stringBuilder.append(arg).append(" ");
            }

            if (args.length < 2) {
                reden = "Geen bericht";
            } else {
                reden = stringBuilder.toString();
            }

            Category category = event.getGuild().getCategoriesByName("tickets", false).get(0);

            AuditableRestAction chann = event.getGuild().getController().createTextChannel("ticket-" + event.getMember().getUser().getName())
                    .setTopic("Bedankt om bij ons een ticket te maken.")
                    .setParent(category);

            chann.reason("Ticket Systeem:Channel - Bot").queue();

//            TextChannel channel1 = Main.getJda().getTextChannelsByName("ticket-" + event.getMember().getUser().getName(), true).get(0);
            Channel channel1 = (Channel) chann;

            channel1.createPermissionOverride(event.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();
            channel1.createPermissionOverride(event.getGuild().getRoleById("593198778157367297")).setAllow(Permission.VIEW_CHANNEL).queue();
//            channel1.createPermissionOverride(event.getGuild().getRoleById("593074169412059167")).setAllow(Permission.VIEW_CHANNEL).queue();
//            channel1.createPermissionOverride(event.getGuild().getRoleById("593154609548361750")).setAllow(Permission.VIEW_CHANNEL).queue();
//            channel1.createPermissionOverride(event.getGuild().getRoleById("593074171269873694")).setAllow(Permission.VIEW_CHANNEL).queue();
//            channel1.createPermissionOverride(event.getGuild().getRoleById("593074286093402132")).setAllow(Permission.VIEW_CHANNEL).queue();
//            channel1.createPermissionOverride(event.getGuild().getRoleById("593085822857773088")).setAllow(Permission.VIEW_CHANNEL).queue();
//            channel1.createPermissionOverride(event.getGuild().getRoleById("593074338052308994")).setAllow(Permission.VIEW_CHANNEL).queue();
//            channel1.createPermissionOverride(event.getGuild().getRoleById("593077549358120964")).setAllow(Permission.VIEW_CHANNEL).queue();


            channel.sendMessage("U hebt een **ticket** aangemaakt.").queue();
        }
    }
}
