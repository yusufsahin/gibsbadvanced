package tr.gov.gib.taskman.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.gov.gib.taskman.dao.WorkitemRepository;
import tr.gov.gib.taskman.dao.model.Workitem;
import tr.gov.gib.taskman.dto.WorkitemDto;
import tr.gov.gib.taskman.service.WorkitemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkitemServiceImpl implements WorkitemService {

    @Autowired
    private WorkitemRepository workitemRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public WorkitemDto create(WorkitemDto workitemDto) {

        Workitem workitem=mapToEntity(workitemDto);
        Workitem newWorkitem= workitemRepository.save(workitem);
        WorkitemDto workitemResponse= mapToDTO(newWorkitem);
        return workitemResponse;
    }

    @Override
    public WorkitemDto getById(Long id) {

        Workitem workitem= workitemRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Workitem not found"+id));
        return mapToDTO(workitem);
    }

    @Override
    public List<WorkitemDto> getAllWorkitems() {

        List<Workitem> workitems= workitemRepository.findAll();
        return  workitems.stream().map((workitem) -> mapToDTO(workitem)).collect(Collectors.toList());
    }

    @Override
    public WorkitemDto update(WorkitemDto workitemDto) {

        Workitem workitem= workitemRepository.findById(workitemDto.getId()).orElseThrow(()->new EntityNotFoundException("Workitem not found "+workitemDto.getId()));
        modelMapper.map(workitemDto,workitem);

        Workitem updatedWorkitem= workitemRepository.save(workitem);

        return  mapToDTO(updatedWorkitem);

    }

    @Override
    public void deleteById(Long id) {
        workitemRepository.deleteById(id);
    }

    private Workitem mapToEntity(WorkitemDto workitemDto)
    {

        Workitem workitem=modelMapper.map(workitemDto,Workitem.class);
        return workitem;
    }

    private WorkitemDto mapToDTO(Workitem workitem)
    {
        WorkitemDto workitemDto=modelMapper.map(workitem,WorkitemDto.class);
        return workitemDto;
    }

}
