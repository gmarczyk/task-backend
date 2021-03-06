package com.gmarczyk.taskbackend.fees.infrastructure.memory.loader.csv;

import com.gmarczyk.taskbackend.fees.domain.FeeWage;
import com.gmarczyk.taskbackend.shared.CsvFileParser;
import com.gmarczyk.taskbackend.shared.StartupDataLoader;
import com.gmarczyk.taskbackend.transactions.infrastructure.memory.loader.csv.CsvTransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class CsvFeeWagesStartupDataLoader implements StartupDataLoader<FeeWage> {

    public static final String FEEWAGES_CSV_RESOURCE_PATH = "/data/fee_wages.csv";

    private final CsvFileParser<CsvFeeWageDto> csvParser;

    @Override
    public Collection<FeeWage> load() {
        return csvParser.loadCsv(CsvFeeWageDto.class, FEEWAGES_CSV_RESOURCE_PATH).stream()
                        .map(csvDto -> {
                            try {
                                return toFeeWage(csvDto);
                            } catch (ParseException e) {
                                throw new IllegalStateException("Parse exception", e);
                            }
                        })
                        .toList();
    }

    private FeeWage toFeeWage(CsvFeeWageDto csvDto) throws ParseException {
        return new FeeWage(
                BigDecimal.valueOf(csvDto.getBoundaryValue().intValue()),
                BigDecimal.valueOf(CsvTransactionDto.TRANSACTION_AMOUNT_FORMAT.parse(csvDto.getFeePercentageValue()).doubleValue())
        );
    }
}
