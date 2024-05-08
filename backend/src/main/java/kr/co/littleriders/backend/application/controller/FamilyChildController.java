package kr.co.littleriders.backend.application.controller;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.application.dto.response.ChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.ChildListResponse;
import kr.co.littleriders.backend.application.facade.FamilyChildFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthFamily;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/family/child")
@RequiredArgsConstructor
public class FamilyChildController {

	private final FamilyChildFacade familyChildFacade;

	@PostMapping
	public ResponseEntity<Long> addChild(@Auth AuthFamily authFamily,
										@ModelAttribute @Valid ChildRegistRequest childRegistRequest) {

		Long familyId = authFamily.getId();
		Long childId = familyChildFacade.insertChild(childRegistRequest, familyId);

		return ResponseEntity.ok().body(childId);
	}

	@GetMapping
	public ResponseEntity<List<ChildListResponse>> getChildList(@Auth AuthFamily authFamily) {

		Long familyId = authFamily.getId();
		List<ChildListResponse> childList = familyChildFacade.readChildList(familyId);

		return ResponseEntity.ok().body(childList);
	}

	@GetMapping("/{childId}")
	public ResponseEntity<ChildDetailResponse> getChildDetail(@Auth AuthFamily authFamily,
											@PathVariable(value = "childId") Long childId) {

		Long familyId = authFamily.getId();
		ChildDetailResponse childDetail = familyChildFacade.readChildDetail(familyId, childId);

		return ResponseEntity.ok().body(childDetail);
	}

	@GetMapping("/{childId}/image")
	public ResponseEntity<Resource> getChildImage(@Auth AuthFamily authFamily,
												  @PathVariable(value = "childId") Long childId) {

		Long familyId = authFamily.getId();

		Map<String, Object> image = familyChildFacade.readChildImage(familyId, childId);

		Resource imageResource = (Resource) image.get("resource");
		MediaType mediaType = (MediaType) image.get("mediaType");

		HttpHeaders headers = new HttpHeaders();
		if (mediaType != null) {
			headers.setContentType(mediaType);
		} else {
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		}

		return ResponseEntity.ok().headers(headers).body(imageResource);
	}
}