package step4.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
	private static final int LOTTO_NUMBER_LENGTH = 6;
	private static final String LOTTO_PHRASES = "로또 번호는 중복되지 않는 6개의 숫자로 이루어져야 합니다.";

	private final Set<LottoNumber> numbers;

	public Lotto(List<LottoNumber> numbers) {
		validLotto(numbers);
		this.numbers = new LinkedHashSet<>(numbers);
	}

	public static Lotto manual(String inputNumbers) {
		return new Lotto(Arrays.stream(inputNumbers.split(", "))
				.map(Integer::new)
				.map(LottoNumber::new)
				.collect(Collectors.toList())
		);
	}

	public static Lotto auto() {
		return new Lotto(LottoNumber.getRandomNumbers(LOTTO_NUMBER_LENGTH));
	}

	private void validLotto(List<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_NUMBER_LENGTH) {
			throw new IllegalArgumentException(LOTTO_PHRASES);
		}
	}

	public int getMatchCount(Lotto otherLotto) {
		return (int) numbers.stream()
				.filter(otherLotto::containNumber)
				.count();
	}

	public boolean containNumber(LottoNumber number) {
		return numbers.contains(number);
	}

	@Override
	public String toString() {
		return Arrays.toString(numbers.toArray());
	}
}