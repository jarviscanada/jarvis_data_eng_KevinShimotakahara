#!/bin/bash

# Script usage
#bash scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password

# Example
#bash scripts/host_usage.sh localhost 5432 host_agent postgres password

#error function
errorf(){
  echo $1 1>&2
  exit 1
}

#validate arguments
if [ "$#" -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

#assign CLI arguments to variables (e.g. `psql_host=$1`)
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#refrain from needing to manually enter password
export PGPASSWORD=$psql_password

#save hostname to a variable
hostname="$(hostname -f)"

#raw usage data
#vmstat --unit M
vmstat_dout=$(vmstat -d)
dfBM_output=$(df -BM /)
vmstat_tout=$(vmstat -t)
meminfo_out=$(cat /proc/meminfo)
#parse server CPU and memory usage data using bash scripts
memory_free=$(echo "$meminfo_out" | egrep "^MemFree:" | awk '{print $2}' | xargs)
if [ "$memory_free" = "" ]; then
  errorf "failed to get memory_free"
fi
cpu_idle=$(echo "$vmstat_tout"  | egrep "^ [0-9]" | awk '{print $15}' | xargs) || errorf "failed to get cpu_idle"
if [ "$cpu_idle" = "" ]; then
  errorf "failed to get cpu_idle"
fi
cpu_kernel=$(echo "$vmstat_tout"  | egrep "^ [0-9]" | awk '{print $14}' | xargs) || errorf "failed to get cpu_kernel"
if [ "$cpu_kernel" = "" ]; then
  errorf "failed to get cpu_kernel"
fi
disk_io=$(echo "$vmstat_dout"  | egrep "^sda" | awk '{print $10}' | xargs) || errorf "failed to get disk_io"
if [ "$disk_io" = "" ]; then
  errorf "failed to get disk_io"
fi
disk_available=$(echo "$dfBM_output" | grep "^/dev/" | awk '{print $4}' | xargs) || errorf "failed to get disk_available"
if [ "$disk_available" = "" ]; then
  errorf "failed to get disk_available"
fi
disk_available=${disk_available%?}
if [ "$disk_available" = "" ]; then
  errorf "failed to get disk_available"
fi
timestamp=$(date -u '+%Y-%m-%d %H:%M:%S') || errorf "failed to get timestamp"
if [ "$timestamp" = "" ]; then
  errorf "failed to get timestamp"
fi
#construct the INSERT statement. (hint: use a subquery to get id by hostname)
nested_select=$(cat <<- _EOF_
(SELECT id FROM host_info WHERE host_name='$hostname')
_EOF_
)
insert_stmt=$(cat <<- _EOF_
INSERT INTO host_usage
  (memory_free,cpu_idle,cpu_kernel,disk_io,disk_available,timestamp,host_id)
VALUES
  ($memory_free,$cpu_idle,$cpu_kernel,$disk_io,$disk_available,'$timestamp',$nested_select);
_EOF_
)

#execute the INSERT statement
psql -h "$psql_host" -p "$psql_port" -U "$psql_user" -d "$db_name" -c "$insert_stmt" || errorf "Failed call to psql."
date
exit 0