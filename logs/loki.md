http://loki:3100

timestamp -> ^(\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{3}[+-]\d{2}:\d{2}) |

level -> \s+(INFO|WARN|ERROR|DEBUG|TRACE)\s+

pid -> \s+(\d+)\s+---

app -> ---\s+\[([^\]]+)\]

thread -> \]\s+\[([^\]]+)\]\s+\S

logger -> \]\s+(\S+)\s+:

message -> : (.+)