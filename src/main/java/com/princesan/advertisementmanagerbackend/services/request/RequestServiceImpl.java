package com.princesan.advertisementmanagerbackend.services.request;

import com.princesan.advertisementmanagerbackend.exceptions.request.NoMatchingBannersException;
import com.princesan.advertisementmanagerbackend.exceptions.request.NoMatchingCategoriesException;
import com.princesan.advertisementmanagerbackend.exceptions.request.NoNewMatchingBannersForTodayException;
import com.princesan.advertisementmanagerbackend.exceptions.request.RequestException;
import com.princesan.advertisementmanagerbackend.model.dtos.BannerDto;
import com.princesan.advertisementmanagerbackend.model.entities.Banner;
import com.princesan.advertisementmanagerbackend.model.entities.Category;
import com.princesan.advertisementmanagerbackend.model.entities.LogRecord;
import com.princesan.advertisementmanagerbackend.repositories.BannerRepository;
import com.princesan.advertisementmanagerbackend.repositories.CategoryRepository;
import com.princesan.advertisementmanagerbackend.repositories.LogRecordRepository;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final LogRecordRepository logRecordRepository;
    private final BannerRepository bannerRepository;
    private final CategoryRepository categoryRepository;
    private final TypeMap<Banner, BannerDto> bannerBannerDtoTypeMap;

    private static final String NO_MATCHING_CATEGORIES_MESSAGE = "Could not find categories with given requestIds";
    private static final String NO_MATCHING_BANNERS_MESSAGE = "Could not find banners under given categories";
    private static final String NO_NEW_MATCHING_BANNERS_FOR_TODAY_MESSAGE =
            "All matching banners were already shown today";

    @Autowired
    public RequestServiceImpl(LogRecordRepository logRecordRepository,
                              BannerRepository bannerRepository,
                              CategoryRepository categoryRepository, TypeMap<Banner, BannerDto> bannerBannerDtoTypeMap) {
        this.logRecordRepository = logRecordRepository;
        this.bannerRepository = bannerRepository;
        this.categoryRepository = categoryRepository;
        this.bannerBannerDtoTypeMap = bannerBannerDtoTypeMap;
    }

    @Override
    public BannerDto requestBanner(String userAgent, String ip, List<String> categories) throws RequestException
    {
        Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date(timestampNow.getTime()));

        LogRecord logRecord = new LogRecord();
        logRecord.setUserAgent(userAgent);
        logRecord.setIpAddress(ip);
        logRecord.setTime(timestampNow);

        List<Category> categoryList =
                categoryRepository.findDistinctByRequestIdInAndDeletedIsFalse(categories);

        logRecord.setCategories(categoryList);

        if (categoryList.isEmpty()) {
            logRecord.setNoContentReason("NO_SUCH_CATEGORIES");
            logRecordRepository.save(logRecord);
            throw new NoMatchingCategoriesException();
        }

        List<Banner> bannerList =
                bannerRepository.findDistinctByCategoriesInAndDeletedIsFalse(categoryList);

        if (bannerList.isEmpty()) {
            logRecord.setNoContentReason("N0_SUCH_BANNERS");
            logRecordRepository.save(logRecord);
            throw new NoMatchingBannersException();
        }

        List<LogRecord> logRecordList =
                logRecordRepository.findAllByUserAgentAndIpAddress(userAgent, ip);

        for (LogRecord record : logRecordList) {
            Date date = new Date(record.getTime().getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (calendarNow.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)
                    && calendarNow.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
                    && record.getBanner() != null) {
                bannerList.remove(record.getBanner());
            }
        }

        if (bannerList.isEmpty()) {
            logRecord.setNoContentReason("EXHAUSTED_BANNERS");
            logRecordRepository.save(logRecord);
            throw new NoNewMatchingBannersForTodayException();
        }

        bannerList.sort(Comparator.comparing(Banner::getPrice).reversed());
        Banner banner = bannerList.get(0);
        logRecord.setBanner(banner);
        logRecord.setPrice(banner.getPrice());
        logRecordRepository.save(logRecord);

        return bannerBannerDtoTypeMap.map(bannerList.get(0));
    }
}
