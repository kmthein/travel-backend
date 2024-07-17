package com.travelbackend.services;

import com.travelbackend.dao.AirLineDAO;
import com.travelbackend.dao.DestinationDAO;
import com.travelbackend.dao.FlightScheduleDAO;
import com.travelbackend.dto.TransportScheduleDTO;
import com.travelbackend.entity.AirLine;
import com.travelbackend.entity.Destination;
import com.travelbackend.entity.FlightSchedule;
import com.travelbackend.dao.TravelPlanDAO;
import com.travelbackend.dto.*;
import com.travelbackend.entity.*;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService{
    @Autowired
    private DestinationDAO destinationDAO;

    @Autowired
    private FlightScheduleDAO flightScheduleDAO;

    @Autowired
    private AirLineDAO airLineDAO;

    @Autowired
    private FlightClassService flightClassService;

    @Autowired
    private TravelPlanDAO travelPlanDAO;

    @Override
    public void create(FlightSchedule flightSchedule,int airlineId, int departureId, int arrivalId) {
        if(departureId == arrivalId){
            throw new ResourceNotFoundException("Departure Place and Arrival Place can not be same!");
        }
        AirLine airLine = airLineDAO.findAirLineById(airlineId);
        Destination departurePlace =destinationDAO.findDestinationById(departureId);
        Destination arrivalPlace = destinationDAO.findDestinationById(arrivalId);
        flightSchedule.setAirLine(airLine);
        flightSchedule.setDeparturePlace(departurePlace);
        flightSchedule.setArrivalPlace(arrivalPlace);
        flightScheduleDAO.save(flightSchedule);
    }

    @Override
    public List<FlightSchedule> getAllFlightSchedule() {
        return flightScheduleDAO.findAll();
    }

    @Override
    public FlightSchedule getFlightScheduleById(int id) {
        return flightScheduleDAO.findFlightScheduleById(id);
    }

    @Override
    public void updateFlightSchedule(int id, FlightSchedule flightSchedule, int airlineId, int departureId, int arrivalId) {
        FlightSchedule currentFlightSchedule = flightScheduleDAO.findFlightScheduleById(id);
        if(departureId == arrivalId){
            throw new ResourceNotFoundException("Departure Place and Arrival Place can not be same!");
        }
        AirLine airLine = airLineDAO.findAirLineById(airlineId);
        Destination departurePlace =destinationDAO.findDestinationById(departureId);
        Destination arrivalPlace = destinationDAO.findDestinationById(arrivalId);
        currentFlightSchedule.setDepartureTime(flightSchedule.getDepartureTime());
        currentFlightSchedule.setArrivalTime(flightSchedule.getArrivalTime());
        currentFlightSchedule.setDate(flightSchedule.getDate());
        currentFlightSchedule.setDistance(flightSchedule.getDistance());
        currentFlightSchedule.setAirLine(airLine);
        currentFlightSchedule.setDeparturePlace(departurePlace);
        currentFlightSchedule.setArrivalPlace(arrivalPlace);
        flightScheduleDAO.update(currentFlightSchedule);
    }

    @Override
    public List<TransportScheduleDTO> getAvailableFlight() {

        List<FlightSchedule> flightScheduleList = flightScheduleDAO.availableFlight();
        List<TransportScheduleDTO> transportScheduleDTOList = new ArrayList<>();
        for (FlightSchedule fs : flightScheduleList) {
            TransportScheduleDTO fst = new TransportScheduleDTO();
            fst.setId(fs.getId());
            fst.setAirlineId(fs.getAirLine().getId());
            fst.setName(fs.getAirLine().getName());
            fst.setDate(fs.getDate());
            fst.setArrivalTime(fs.getArrivalTime());
            fst.setDepartureTime(fs.getDepartureTime());
            fst.setArrivalPlace(fs.getArrivalPlace().getName());
            fst.setDeparturePlace(fs.getDeparturePlace().getName());
            fst.setAriLineImg(fs.getAirLine().getImage());
            transportScheduleDTOList.add(fst);
        }
        return transportScheduleDTOList;
    }
    @Override
    public List<AirlineDTO> getAvailableFlightSchedule(FlightScheduleDTO flightSchDTO) {

        List<FlightSchedule> flightScheduleList = flightScheduleDAO.findAll();
        List<TravelPlan> travelPlanList = travelPlanDAO.findAll();
        List<AirlineDTO> filteredList = new ArrayList<>();

        for(FlightSchedule f : flightScheduleList) {

            if(f.getDeparturePlace().getId() == flightSchDTO.getDeparturePlaceId()
                    && f.getArrivalPlace().getId() == flightSchDTO.getArrivalPlaceId()
                    && f.getDate().equals(flightSchDTO.getDepartureDate())
            ){
                FlightScheduleDTO flightScheduleDTO = new FlightScheduleDTO();
                AirlineDTO airlineDTO = new AirlineDTO();

                AirLine airline = airLineDAO.findAirLineById((f.getAirLine().getId()));
                airlineDTO.setAirlineId(airline.getId());
                airlineDTO.setAirlineName(airline.getName());
                List<String> imgUrlList = new ArrayList<>();
                for(Image i : airline.getImage()) {
                    imgUrlList.add(i.getImgUrl());
                }
                airlineDTO.setImgUrlList(imgUrlList);

                flightScheduleDTO.setFlightScheduleId(f.getId());
                flightScheduleDTO.setAirlineId((f.getAirLine().getId()));
                flightScheduleDTO.setDeparturePlaceId(f.getDeparturePlace().getId());
                flightScheduleDTO.setDeparturePlaceName(f.getDeparturePlace().getName());
                flightScheduleDTO.setArrivalPlaceId(f.getArrivalPlace().getId());
                flightScheduleDTO.setArrivalPlaceName(f.getArrivalPlace().getName());
                flightScheduleDTO.setDistance(f.getDistance());
                flightScheduleDTO.setDepartureDate(f.getDate());
                flightScheduleDTO.setDepartureTime(f.getDepartureTime());
                flightScheduleDTO.setArrivalTime(f.getArrivalTime());

                airlineDTO.setFlightScheduleDTO(flightScheduleDTO);

                List<FlightClassDTO> flightClassDTOList = new ArrayList<>();
                int totalAvailableSeat = 0;

                List<FlightClass> flightClassList = flightClassService.findFlightClassByAirlineId(f.getAirLine().getId());
                for(FlightClass flightClass: flightClassList) {
                    FlightClassDTO flightClassDTO = new FlightClassDTO();
                    flightClassDTO.setFlightClassId(flightClass.getId());
                    flightClassDTO.setFlightClassName(flightClass.getName());
                    flightClassDTO.setPrice(flightClass.getPrice());
                    flightClassDTO.setAvailableSeat(flightClass.getValidSeat());

                    if(travelPlanList != null) {
                        for(TravelPlan t : travelPlanList){
                            if(t.getStartDate().equals(flightSchDTO.getDepartureDate())
                                    && t.getBusClass().getId() == flightClass.getId()
                            ){
                                flightClassDTO.setAvailableSeat(flightClassDTO.getAvailableSeat() -1 );
                            }
                        }
                    }
                    totalAvailableSeat += flightClassDTO.getAvailableSeat();


                    flightClassDTO.setAirlineId(flightClass.getAirline().getId());

                    flightClassDTOList.add(flightClassDTO);
                }
                airlineDTO.setFlightClassDTOList(flightClassDTOList);

                airlineDTO.setHasSeat(totalAvailableSeat > 0);

                filteredList.add(airlineDTO);
            }
        }


        return filteredList.stream().filter(AirlineDTO::isHasSeat).toList();
>>>>>>> dfe6d88aa1ebb74fb415070e375c72d4a857f3ae
    }
}
