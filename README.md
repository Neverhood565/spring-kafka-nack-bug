## To run this project:

1. Start docker compose `.\docker-compose\docker-compose.yml`
2. Run `SpringKafkaNackBugApplication`

## To reproduce issue
1. Start the project

- Expected result: consumer reads and negatively acknowledging messages every 5 seconds. 
- Actual result: consumer reads messages only once, and then stays paused after first `nack()` call.