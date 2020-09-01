#!/bin/bash

# Script usage
#./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
# Example
#./scripts/host_info.sh "localhost" 5432 "host_agent" "postgres" "mypassword"

#error function
errorf(){
  echo $1 1>&2
  exit 1
}

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

#host hardware specs raw data
lscpu_out=$(lscpu)
meminfo_out=$(cat /proc/meminfo)

#parse host hardware specifications using bash cmds
cpu_number=$(echo "$lscpu_out"  | grep -E "^CPU\(s\):" | awk '{print $2}' | xargs)
if [ "$cpu_number" = "" ]; then
  errorf "failed to get cpu_number"
fi
cpu_architecture=$(echo "$lscpu_out"  | grep -E "^Architecture:" | awk '{print $2}' | xargs)
if [ "$cpu_architecture" = "" ]; then
  errorf "failed to get cpu_architecture"
fi
cpu_model=$(echo "$lscpu_out"  | grep -E "^Model name:" | cut -d ':' -f 2 | xargs)
if [ "$cpu_model" = "" ]; then
  errorf "failed to get cpu_model"
fi
cpu_mhz=$(echo "$lscpu_out"  | grep -E "^CPU MHz:" | awk '{print $3}' | xargs)
if [ "$cpu_mhz" = "" ]; then
  errorf "failed to get cpu_mhz"
fi
cpu_mhz=$(echo "$cpu_mhz" | cut -d '.' -f 1)
if [ "$cpu_mhz" = "" ]; then
  errorf "failed to get cpu_mhz"
fi
l2_cache=$(echo "$lscpu_out"  | grep -E "^L2 cache:" | awk '{print $3}' | xargs)
if [ "$l2_cache" = "" ]; then
  errorf "failed to get l2_cache"
fi
l2_cache=${l2_cache%?}
if [ "$l2_cache" = "" ]; then
  errorf "failed to get l2_cache"
fi
total_mem=$(echo "$meminfo_out" | grep -E "^MemTotal:" | awk '{print $2}' | xargs)
if [ "$total_mem" = "" ]; then
  errorf "failed to get total_mem"
fi
timestamp=$(date -u '+%Y-%m-%d %H:%M:%S') #current timestamp in `2019-11-26 14:40:19` format
if [ "$timestamp" = "" ]; then
  errorf "failed to get timestamp"
fi

#construct the INSERT statement
insert_stmt=$(cat <<- _EOF_
INSERT INTO host_info
  (host_name,cpu_number,cpu_architecture,cpu_model,cpu_mhz,l2_cache,total_mem,timestamp)
VALUES
  ('$hostname',$cpu_number,'$cpu_architecture','$cpu_model',$cpu_mhz,$l2_cache,$total_mem,'$timestamp');
_EOF_
)

#execute the INSERT statement through psql CLI tool
psql -h "$psql_host" -p "$psql_port" -U "$psql_user" -d "$db_name" -c "$insert_stmt" || errorf "Failed call to psql."

exit 0