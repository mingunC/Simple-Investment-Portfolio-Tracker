package com.cmgg919.tracker.controller;

import com.cmgg919.tracker.model.Asset;
import com.cmgg919.tracker.service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assets")
@CrossOrigin(origins = "http://localhost:4200") // Angular 앱 CORS 허용
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    // 모든 자산 조회 (더미 사용자 ID 사용)
    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        // 실제 앱에서는 인증된 사용자 ID를 사용해야 함
        List<Asset> assets = assetService.getAssetsByUserId("dummyUser");
        return ResponseEntity.ok(assets);
    }

    // 특정 자산 조회
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        return assetService.getAssetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 새로운 자산 추가
    @PostMapping
    public ResponseEntity<Asset> addAsset(@RequestBody Asset asset) {
        Asset newAsset = assetService.addAsset(asset);
        return new ResponseEntity<>(newAsset, HttpStatus.CREATED);
    }

    // 자산 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset asset) {
        try {
            Asset updatedAsset = assetService.updateAsset(id, asset);
            return ResponseEntity.ok(updatedAsset);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 자산 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }

    // 포트폴리오 총 가치 조회
    @GetMapping("/total-value")
    public ResponseEntity<Map<String, BigDecimal>> getPortfolioTotalValue() {
        BigDecimal totalValue = assetService.calculatePortfolioTotalValue("dummyUser");
        return ResponseEntity.ok(Map.of("totalValue", totalValue));
    }

    // 포트폴리오 수익/손실 조회
    @GetMapping("/profit-loss")
    public ResponseEntity<Map<String, BigDecimal>> getPortfolioProfitLoss() {
        BigDecimal profitLoss = assetService.calculateProfitLoss("dummyUser");
        return ResponseEntity.ok(Map.of("profitLoss", profitLoss));
    }

    // 자산 유형별 비중 조회 (예: /api/assets/type-percentage?type=Stock)
    @GetMapping("/type-percentage")
    public ResponseEntity<Map<String, BigDecimal>> getAssetTypePercentage(@RequestParam String type) {
        BigDecimal percentage = assetService.calculateAssetTypePercentage("dummyUser", type);
        return ResponseEntity.ok(Map.of(type + "Percentage", percentage));
    }
}