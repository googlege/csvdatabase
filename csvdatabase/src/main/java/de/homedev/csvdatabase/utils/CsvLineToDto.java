package de.homedev.csvdatabase.utils;

public interface CsvLineToDto<T> {
	T lineToDto(String line);
}
