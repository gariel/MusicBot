package com.jagrosh.jmusicbot.custom;

import com.jagrosh.jmusicbot.BotConfig;
import com.jagrosh.jmusicbot.audio.RequestMetadata;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import java.io.*;

public class MusicPlayerLog {

    private OutputStreamWriter stream;

    public MusicPlayerLog(BotConfig config)
    {
        String filename = config.getCsvLogFile();
        if (!filename.isEmpty())
        {
            try
            {
                File f = new File(filename);
                boolean newFile = !f.exists();
                FileOutputStream file = new FileOutputStream(f);
                stream = new OutputStreamWriter(file);

                if (newFile)
                {
                    stream.write("user;author;name;url\n");
                    stream.flush();
                }
            }
            catch (IOException ignored)
            {
            }
        }
    }

    public void addListenedTrack(AudioTrackInfo info, RequestMetadata rm)
    {
        if (stream != null)
        {
            try
            {
                stream.write(rm.user.username + ";" + info.author + ";" + info.title + ";" + info.uri + "\n");
                stream.flush();
            }
            catch (IOException ignored)
            {
            }
        }
    }
}
