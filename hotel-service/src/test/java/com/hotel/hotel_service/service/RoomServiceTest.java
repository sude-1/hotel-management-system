package com.hotel.hotel_service.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.hotel.hotel_service.entity.Room;
import com.hotel.hotel_service.repository.RoomRepository;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    void shouldReturnAllRooms() {

        // Arrange
        List<Room> rooms = List.of(
                new Room("101", "STANDARD", 75, true),
                new Room("102", "DELUXE", 120, true)
        );

        when(roomRepository.findAll()).thenReturn(rooms);

        // Act
        List<Room> result = roomService.getAllRooms();

        // Assert
        assertEquals(2, result.size());
        assertEquals("101", result.get(0).getRoomNumber());
        assertEquals("102", result.get(1).getRoomNumber());
    }

    @Test
void shouldCreateRoom() {

    // Arrange
    Room room = new Room("103", "DELUXE", 150, true);

    when(roomRepository.save(room)).thenReturn(room);

    // Act
    Room result = roomService.createRoom(room);

    // Assert
    assertEquals("103", result.getRoomNumber());
    assertEquals("DELUXE", result.getRoomType());
    assertEquals(150, result.getPricePerNight());

    org.mockito.Mockito.verify(roomRepository).save(room);
    }

    @Test
void shouldThrowExceptionWhenRoomDoesNotExist() {

    // Act & Assert
    ResponseStatusException exception = assertThrows(
            ResponseStatusException.class,
            () -> roomService.getRoomById(999L)
    );

    assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
void shouldUpdateRoom() {

    // Arrange
    Room existingRoom = new Room("101", "STANDARD", 75, true);

    Room updatedRoom = new Room("101", "DELUXE", 120, true);

    when(roomRepository.findById(1L))
            .thenReturn(java.util.Optional.of(existingRoom));

    when(roomRepository.save(existingRoom))
            .thenReturn(existingRoom);

    // Act
    Room result = roomService.updateRoom(1L, updatedRoom);

    // Assert
    assertEquals("DELUXE", result.getRoomType());
    assertEquals(120, result.getPricePerNight());

    org.mockito.Mockito.verify(roomRepository).save(existingRoom);
    }

    @Test
void shouldDeleteRoom() {

    // Arrange
    Room existingRoom = new Room("101", "STANDARD", 75, true);

    when(roomRepository.findById(1L))
            .thenReturn(java.util.Optional.of(existingRoom));

    // Act
    roomService.deleteRoom(1L);

    // Assert
    org.mockito.Mockito.verify(roomRepository).delete(existingRoom);
    }
}