package com.travelbackend.services;

import com.travelbackend.dao.BusScheduleDAO;
import com.travelbackend.dao.BusServiceDAO;
import com.travelbackend.dao.DestinationDAO;
import com.travelbackend.dto.TransportScheduleDTO;
import com.travelbackend.dao.TravelPlanDAO;
import com.travelbackend.dto.BusClassDTO;
import com.travelbackend.dto.BusScheduleDTO;
import com.travelbackend.dto.BusServiceDTO;
import com.travelbackend.entity.*;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusScheduleServiceImpl implements BusScheduleService {
    @Autowired
    private BusServiceDAO busServiceDAO;

    @Autowired
    private DestinationDAO destinationDAO;

    @Autowired
    private BusScheduleDAO busScheduleDAO;

    @Autowired
    private BusClassService busClassService;

    @Autowired
    private TravelPlanDAO travelPlanDAO;

    @Override
    public void create(BusSchedule busSchedule, int busId, int departureId, int arrivalId) {
        if (departureId == arrivalId) {
            throw new ResourceNotFoundException("Departure Place and Arrival Place can not be same!");
        }
        BusService bus = busServiceDAO.findBusServiceById(busId);
        Destination departurePlace =destinationDAO.findDestinationById(departureId);
        Destination arrivalPlace = destinationDAO.findDestinationById(arrivalId);
        busSchedule.setBusService(bus);
        busSchedule.setDeparturePlace(departurePlace);
        busSchedule.setArrivalPlace(arrivalPlace);
        busScheduleDAO.save(busSchedule);

    }

    @Override
    public List<BusSchedule> getAllBusSchedule() {
        return busScheduleDAO.findAll();
    }

    @Override
    public BusSchedule getBusScheduleById(int id) {
        return busScheduleDAO.findBusScheduleById(id);
    }

    @Override
    public void updateBusSchedule(int id, BusSchedule busSchedule, int busId, int departureId, int arrivalId) {
        BusSchedule currentBusSchedule = busScheduleDAO.findBusScheduleById(id);
        if(departureId == arrivalId){
            throw new ResourceNotFoundException("Departure Place and Arrival Place can not be same!");
        }
        BusService busService = busServiceDAO.findBusServiceById(busId);
        Destination departurePlace =destinationDAO.findDestinationById(departureId);
        Destination arrivalPlace = destinationDAO.findDestinationById(arrivalId);
        currentBusSchedule.setDepartureTime(busSchedule.getDepartureTime());
        currentBusSchedule.setArrivalTime(busSchedule.getArrivalTime());
        currentBusSchedule.setDate(busSchedule.getDate());
        currentBusSchedule.setDistance(busSchedule.getDistance());
        currentBusSchedule.setBusService(busService);
        currentBusSchedule.setDeparturePlace(departurePlace);
        currentBusSchedule.setArrivalPlace(arrivalPlace);
        busScheduleDAO.update(currentBusSchedule);
    }

    @Override
    public List<TransportScheduleDTO> getAvailableFlight() {
        List<BusSchedule> busScheduleList = busScheduleDAO.availableBus();
        List<TransportScheduleDTO> transportScheduleDTOList = new ArrayList<>();
        for (BusSchedule bs : busScheduleList) {
            TransportScheduleDTO bst = new TransportScheduleDTO();
            bst.setId(bs.getId());
            bst.setName(bs.getBusService().getName());
            bst.setDate(bs.getDate());
            bst.setArrivalTime(bs.getArrivalTime());
            bst.setDepartureTime(bs.getDepartureTime());
            bst.setArrivalPlace(bs.getArrivalPlace().getName());
            bst.setDeparturePlace(bs.getDeparturePlace().getName());
            bst.setAriLineImg(bs.getBusService().getImage());
            transportScheduleDTOList.add(bst);
        }
        return transportScheduleDTOList;
    }

        @Override
        public List<BusServiceDTO> getAvailableBusSchedule(BusScheduleDTO busSchDTO) {

            List<BusSchedule> busScheduleList = busScheduleDAO.findAll();
            List<TravelPlan> travelPlanList = travelPlanDAO.findAll();
            List<BusServiceDTO> filteredList = new ArrayList<>();

            for(BusSchedule b : busScheduleList) {
                System.out.println(b.getDate());
                System.out.println(busSchDTO.getDepartureDate());

                if(b.getDeparturePlace().getId() == busSchDTO.getDeparturePlaceId()
                        && b.getArrivalPlace().getId() == busSchDTO.getArrivalPlaceId()
                        && b.getDate().equals(busSchDTO.getDepartureDate())
                ){
                    BusScheduleDTO busScheduleDTO = new BusScheduleDTO();
                    BusServiceDTO busServiceDTO = new BusServiceDTO();

                    BusService busService = busServiceDAO.findBusServiceById(b.getBusService().getId());
                    busServiceDTO.setBusServiceId(busService.getId());
                    busServiceDTO.setBusServiceName(busService.getName());
                    List<String> imgUrlList = new ArrayList<>();
                    for(Image i : busService.getImage()) {
                        imgUrlList.add(i.getImgUrl());
                    }
                    busServiceDTO.setImgUrlList(imgUrlList);

                    busScheduleDTO.setBusScheduleId(b.getId());
                    busScheduleDTO.setBusServiceId((b.getBusService().getId()));
                    busScheduleDTO.setDeparturePlaceId(b.getDeparturePlace().getId());
                    busScheduleDTO.setDeparturePlaceName(b.getDeparturePlace().getName());
                    busScheduleDTO.setArrivalPlaceId(b.getArrivalPlace().getId());
                    busScheduleDTO.setArrivalPlaceName(b.getArrivalPlace().getName());
                    busScheduleDTO.setDistance(b.getDistance());
                    busScheduleDTO.setDepartureDate(b.getDate());
                    busScheduleDTO.setDepartureTime(b.getDepartureTime());
                    busScheduleDTO.setArrivalTime(b.getArrivalTime());

                    busServiceDTO.setBusScheduleDTO(busScheduleDTO);

                    List<BusClassDTO> busClassDTOList = new ArrayList<>();
                    int totalAvailableSeat = 0;

                    List<BusClass> busClassList = busClassService.findBusClassByBusServiceId(b.getBusService().getId());
                    for(BusClass busClass: busClassList) {
                        BusClassDTO busClassDTO = new BusClassDTO();
                        busClassDTO.setBusClassId(busClass.getId());
                        busClassDTO.setBusClassName(busClass.getName());
                        busClassDTO.setPrice(busClass.getPrice());
                        busClassDTO.setAvailableSeat(busClass.getValidSeat());

                        if(travelPlanList != null) {
                            for(TravelPlan t : travelPlanList){
                                if(t.getStartDate().equals(busSchDTO.getDepartureDate())
                                        && t.getBusClass().getId() == busClass.getId()
                                ){
                                    busClassDTO.setAvailableSeat(busClassDTO.getAvailableSeat() -1 );
                                }
                            }
                        }
                        totalAvailableSeat += busClassDTO.getAvailableSeat();


                        busClassDTO.setBusServiceId(busClass.getBusService().getId());

                        busClassDTOList.add(busClassDTO);
                    }
                    busServiceDTO.setBusClassDTOList(busClassDTOList);

                    busServiceDTO.setHasSeat(totalAvailableSeat > 0);

                    filteredList.add(busServiceDTO);
                }
            }


            return filteredList.stream().filter(BusServiceDTO::isHasSeat).toList();
        }
    }
