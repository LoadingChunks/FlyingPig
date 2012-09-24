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
		if((r.getLevel() == Level.SEVERE /*|| r.getLevel() == Level.WARNING*/) && !(r.getLoggerName().equalsIgnoreCase("FlyingPig")))
		{
			HoptoadNoticeBuilder nb;
			try {
				Throwable t = r.getThrown();

				try {
					nb = new HoptoadNoticeBuilder("7039ac7f7d5a9a8e6dceff5b4a0d263d", t, "dev");
				} catch (NullPointerException e)
				{
					nb = new HoptoadNoticeBuilder("7039ac7f7d5a9a8e6dceff5b4a0d263d", r.getMessage(), "dev");
				}

				HoptoadNotice n = nb.newNotice();
				HoptoadNotifier nt = new HoptoadNotifier();
				nt.notify(n, "errbit.loadingchunks.net/notifier_api/v2/notices", false);
			} catch (Exception e)
			{
				this.plugin.getServer().getLogger().removeHandler(this);
				e.printStackTrace();
				this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
			}
		}
	}
}
