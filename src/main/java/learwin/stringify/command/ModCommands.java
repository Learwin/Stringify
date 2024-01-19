package learwin.stringify.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.event.RegisterCommandsEvent;

import java.util.ArrayList;

public class ModCommands {

    public static final ArrayList<BaseCommand> commands = new ArrayList<>();

    public static void registerCommands(final RegisterClientCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        commands.add(new DefineDelimiterCommand("setDelimiter", 0));
        commands.add(new IgnoreDuplicateCommand("toggleDuplicates", 0));
        commands.forEach(commands -> {
            if (commands.setExecution() != null) {
                dispatcher.register(commands.getBuilder());
            }
        });
    }

}
