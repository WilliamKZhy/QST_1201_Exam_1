package QstHive.QstHive;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MapReduceEx3 {
	public static class Map extends Mapper<LongWritable, Text, Text, Text> {
		private HashSet<String> ipset = new HashSet<String>();
		public void map(LongWritable key, Text values, Context context) throws IOException, InterruptedException {
			String pattern = "(\\d+.\\d+.\\d+.\\d+)(.*)";
			Pattern r = Pattern.compile(pattern); // 创建 Pattern 对象
			String strIp = null;
			String line = values.toString();
			Matcher m = r.matcher(line);// 把获取的数据放入正则表达式中筛选
			// 切分获取IP
			if (m.find()) {
				strIp = m.group(0);
			}
			ipset.add(strIp);
			}
			String number = "allnumber:"+ipset.size();
			context.write(new Text(), new Text(number));
		}

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		conf.set("custom_ips_hdfs_path", args[2]);
		Job job = Job.getInstance(conf);
		job.setJarByClass(MapReduceEx3.class);
		job.setMapperClass(Map.class);
		job.setNumReduceTasks(0);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		boolean status = job.waitForCompletion(true);
		return;
	}
}
