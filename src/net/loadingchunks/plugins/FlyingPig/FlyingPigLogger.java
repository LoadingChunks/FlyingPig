package net.loadingchunks.plugins.FlyingPig;

import java.util.logging.*;

import hoptoad.HoptoadNotice;
import hoptoad.HoptoadNoticeBuilder;
import hoptoad.HoptoadNotifier;

public class FlyingPigLogger extends Handler {
	
	private FlyingPig plugin;
	
	public void setPlugin(FlyingPig plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public void close() throws SecurityException {
		
	}

	@Override
	public void flush() {
	}

	@Override
	public void publish(LogRecord r) {
		if((r.getLevel() == Level.SEVERE || r.getLevel() == Level.WARNING) && !(r.getLoggerName().equalsIgnoreCase("FlyingPig")))
		{
			try {
				Throwable t = r.getThrown();
				
				String error = "";
				
				try {
					error = t.getMessage();
				} catch (NullPointerException e)
				{
					error = r.getMessage();
				}
				
				HoptoadNoticeBuilder nb = new HoptoadNoticeBuilder("7039ac7f7d5a9a8e6dceff5b4a0d263d", error, "dev");
				HoptoadNotice n = nb.newNotice();
				HoptoadNotifier nt = new HoptoadNotifier();
//				this.plugin.log.info("Connecting to Errbit on " + this.plugin.getConfig().getString("airbrake.host") + "/notifier_api/v2/notices using key " + this.plugin.getConfig().getString("airbrake.key"));
				nt.notify(n, "http://errbit.loadingchunks.net/notifier_api/v2/notices", false);
			} catch (Exception e)
			{
				this.plugin.getServer().getLogger().removeHandler(this);
				e.printStackTrace();
				this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
			}
		}
	}
}
