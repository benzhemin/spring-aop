# Spring Boot Logs with Loki + Grafana

## Quick Start

### 1. Start the Stack
```bash
docker-compose up -d
```

This will start:
- **Loki** on port 3100 (log storage)
- **Promtail** (log shipper)
- **Grafana** on port 3000 (visualization)

### 2. Access Grafana
Open your browser to: `http://localhost:3000`

**Default credentials:**
- Username: `admin`
- Password: `admin`

## Using Grafana

### Explore Logs
1. Click **Explore** in the left sidebar
2. Make sure "Loki" is selected as the datasource
3. Use LogQL queries to filter logs

### LogQL Query Examples

**View all Spring Boot logs:**
```
{job="spring-boot"}
```

**Only ERROR logs:**
```
{job="spring-boot"} | level="ERROR"
```

**Only WARN and ERROR logs:**
```
{job="spring-boot"} | level=~"ERROR|WARN"
```

**From a specific logger:**
```
{job="spring-boot", logger="c.e.springaop.SpringAopLearnApplication"}
```

**Search for specific text (e.g., exception):**
```
{job="spring-boot"} | "NullPointerException"
```

**Search by thread:**
```
{job="spring-boot", thread="main"}
```

**Combine multiple filters:**
```
{job="spring-boot"} | level="ERROR" | "failed"
```

### Color Coding
Logs are color-coded by level:
- 🔴 **ERROR** - Red
- 🟡 **WARN** - Yellow
- 🔵 **INFO** - Blue
- ⚪ **DEBUG** - Gray

## Files Included

- `docker-compose.yml` - Docker services configuration
- `loki-config.yml` - Loki storage and indexing config
- `promtail-config.yml` - Log parsing and shipping config
- `grafana-provisioning/` - Auto-configured datasources and dashboards

## Managing Logs

### Add More Log Files
Edit `promtail-config.yml` and add paths in the volumes section of `docker-compose.yml`:

```yaml
volumes:
  - ./your-log-file.log:/var/log/spring-boot/your-log-file.log:ro
```

Then restart Promtail:
```bash
docker-compose restart promtail
```

### Stop the Stack
```bash
docker-compose down
```

### View Logs (Docker)
```bash
docker-compose logs -f grafana
docker-compose logs -f loki
docker-compose logs -f promtail
```

## Troubleshooting

### No logs appearing in Grafana?

1. Check Promtail is running:
```bash
docker-compose logs promtail
```

2. Verify Loki is receiving data:
```bash
curl -s http://localhost:3100/loki/api/v1/label/job/values
```

3. Restart all services:
```bash
docker-compose down
docker-compose up -d
```

### Check Loki Status
```bash
curl -s http://localhost:3100/ready
```

### Change Grafana Password
1. Go to Grafana → Admin → Settings
2. Change password in user settings

## Advanced Features

### Create Alerts
In Grafana, you can set up alerts based on LogQL queries.

### Create Custom Dashboards
1. Go to Dashboards → Create New
2. Add Loki panels with custom queries
3. Save the dashboard

### Export/Import Dashboards
Use Grafana's built-in export/import functionality to share dashboards.
