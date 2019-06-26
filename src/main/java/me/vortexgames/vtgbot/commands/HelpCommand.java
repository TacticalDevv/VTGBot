package me.vortexgames.vtgbot.commands;

import me.vortexgames.vtgbot.CommandManager;
import me.vortexgames.vtgbot.Constants;
import me.vortexgames.vtgbot.Main;
import me.vortexgames.vtgbot.objects.ICommand;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class HelpCommand implements ICommand {

    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {

        if (args.isEmpty()) {
            generateAndSendEmbed(event);
            return;
        }

        ICommand command = manager.getCommand(String.join("", args));

        if (command == null) {
            event.getChannel().sendMessage("Shows a list of al the commands.\n" +
                    "Usage: `" + Constants.PREFIX + getInvoke() + " [command]`").queue();
            return;
        }

        String message = "Command help voor `" + command.getInvoke() + "`\n\n" + command.getHelp();

        event.getChannel().sendMessage(message).queue();

    }

    private void generateAndSendEmbed(GuildMessageReceivedEvent event) {

        EmbedBuilder builder = new EmbedBuilder()
                .setTitle("Een lijst met al mijn commands:").setColor(Main.getRandomColor());

        StringBuilder descriptionBuilder = builder.getDescriptionBuilder();

        manager.getCommands().forEach(
                (command) -> descriptionBuilder.append('`').append(command.getInvoke()).append("`\n")
        );

        event.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public String getHelp() {
        return "Shows a list of al the commands.\n" +
                "Usage: `" + Constants.PREFIX + getInvoke() + " [command]`";
    }

    @Override
    public String getInvoke() {
        return "help";
    }
}
