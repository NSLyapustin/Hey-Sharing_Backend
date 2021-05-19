package ru.itis.sharing.service.RentSerivce;

import javassist.NotFoundException;
import ru.itis.sharing.dto.Log.LogDto;

public interface RentService {
    public void addNewLog(LogDto logDto) throws NotFoundException;
}
