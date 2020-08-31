SELECT cpu_number,id,total_mem
	FROM public.host_info
ORDER BY cpu_number, total_mem desc;

CREATE OR REPLACE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

SELECT host_id, host_name,
       round5(host_usage.timestamp) AS timestamp5min,
       round(AVG((total_mem*1000 :: float - memory_free :: float)/(total_mem*10))) AS avg_used_mem_percentage
	FROM host_usage
		INNER JOIN host_info
		ON host_usage.host_id = host_info.id
    GROUP BY host_id, host_name, timestamp5min, total_mem, memory_free
	ORDER BY host_id;

