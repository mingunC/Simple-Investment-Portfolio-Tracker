package com.cmgg919.tracker.service;

import com.cmgg919.tracker.model.Asset;
import com.cmgg919.tracker.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAssetsByUserId(String userId) {
        return assetRepository.findByUserId(userId);
    }

    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }

    public Asset addAsset(Asset asset) {
        // 더미 사용자 ID 설정 (실제 앱에서는 인증 후 사용자 ID 사용)
        if (asset.getUserId() == null || asset.getUserId().isEmpty()) {
            asset.setUserId("dummyUser");
        }
        return assetRepository.save(asset);
    }

    public Asset updateAsset(Long id, Asset updatedAsset) {
        return assetRepository.findById(id)
                .map(asset -> {
                    asset.setName(updatedAsset.getName());
                    asset.setType(updatedAsset.getType());
                    asset.setQuantity(updatedAsset.getQuantity());
                    asset.setPurchasePrice(updatedAsset.getPurchasePrice());
                    asset.setCurrentPrice(updatedAsset.getCurrentPrice());
                    return assetRepository.save(asset);
                })
                .orElseThrow(() -> new RuntimeException("Asset not found with id " + id));
    }

    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }

    // 포트폴리오 총 가치 계산
    public BigDecimal calculatePortfolioTotalValue(String userId) {
        List<Asset> assets = assetRepository.findByUserId(userId);
        return assets.stream()
                .map(asset -> asset.getQuantity().multiply(asset.getCurrentPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 자산 유형별 비중 계산 (간단한 예시)
    public BigDecimal calculateAssetTypePercentage(String userId, String type) {
        List<Asset> assets = assetRepository.findByUserId(userId);
        BigDecimal totalValue = calculatePortfolioTotalValue(userId);

        if (totalValue.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal typeValue = assets.stream()
                .filter(asset -> asset.getType().equalsIgnoreCase(type))
                .map(asset -> asset.getQuantity().multiply(asset.getCurrentPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return typeValue.divide(totalValue, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
    }

    // 수익/손실 계산 (총 수익/손실)
    public BigDecimal calculateProfitLoss(String userId) {
        List<Asset> assets = assetRepository.findByUserId(userId);
        BigDecimal totalCurrentValue = BigDecimal.ZERO;
        BigDecimal totalPurchaseValue = BigDecimal.ZERO;

        for (Asset asset : assets) {
            totalCurrentValue = totalCurrentValue.add(asset.getQuantity().multiply(asset.getCurrentPrice()));
            totalPurchaseValue = totalPurchaseValue.add(asset.getQuantity().multiply(asset.getPurchasePrice()));
        }
        return totalCurrentValue.subtract(totalPurchaseValue);
    }
}