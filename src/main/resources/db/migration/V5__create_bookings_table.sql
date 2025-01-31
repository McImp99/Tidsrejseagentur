CREATE TABLE bookings (
    id INT NOT NULL AUTOINCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    time_machine_id INT NOT NULL,
    time_period_id INT NOT NULL,
    guide_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (time_machine_id) REFERENCES time_machines(id),
    FOREIGN KEY (time_period_id) REFERENCES time_periods(id),
    FOREIGN KEY (guide_id) REFERENCES guides(id)
);
