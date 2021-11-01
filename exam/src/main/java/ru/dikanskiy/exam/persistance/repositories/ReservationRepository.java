package ru.dikanskiy.exam.persistance.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dikanskiy.exam.persistance.entities.Reservation;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    Page<Reservation> findByPersonUsername(final String username, Pageable pageable);

    Page<Reservation> findByAvailableTrue(Pageable pageable);

    List<Reservation> findReservationByValidTrueOrderByReservationTimeAsc();

}
