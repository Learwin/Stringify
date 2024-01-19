package learwin.stringify.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import learwin.stringify.client.LocalDelimiter;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class DefineDelimiterCommand extends BaseCommand{

    public DefineDelimiterCommand(String name, int permLevel) {
        super(name, permLevel);
    }

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> setExecution() {
        return builder.then(Commands.argument(name, StringArgumentType.word())
                .then(Commands.argument("delimiter", StringArgumentType.string())
                        .executes((source -> execute(source.getSource(), StringArgumentType.getString(source, "delimiter"))))));
    }

    private int execute(CommandSourceStack source, String delimiter) {
        LocalDelimiter.setDelimiter(delimiter);
        Minecraft.getInstance().player.sendSystemMessage(Component.translatable("stringify.setdelimiter.message").append(": " + delimiter));
        return Command.SINGLE_SUCCESS;
    }
}
