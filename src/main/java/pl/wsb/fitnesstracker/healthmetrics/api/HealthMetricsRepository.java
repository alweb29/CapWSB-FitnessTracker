package pl.wsb.fitnesstracker.healthmetrics.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthMetricsRepository extends JpaRepository<HealthMetrics, Long> {
}
